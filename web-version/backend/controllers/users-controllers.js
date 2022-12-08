const jsonwebtoken = require("jsonwebtoken");

const { User } = require("../models");
const comparePasswords = require("../util/passwordCompare.js");

//------------------------------------------------------------------------------------GET /api/users/?bucketNum=int
const getUsers = async (req, res, next) => {
  const USERS_BUCKET_SIZE = 100;
  const bucketNum = +req.query.bucketNum || 0;
  User.findAll({
    attributes: { exclude: ["password"] },
    offset: bucketNum * USERS_BUCKET_SIZE,
    limit: USERS_BUCKET_SIZE,
  })
    .then((users) => {
      res.status(200).json({ users });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(500).json({ error: "un error occured" });
    });
};

//------------------------------------------------------------------------------------POST /api/users/login
const login = async (req, res, next) => {
  const { email, password } = req.body;

  User.findOne({ where: { email } })
    .then((user) => {
      if (!user) return res.status(404).json({ message: "email dont exist" });

      if (!comparePasswords(false, password, user.password))
        return res.status(400).json({ message: "wrong password" });

      const token = jsonwebtoken.sign(
        { userId: user.id, userEmail: user.email, userRole: user.RoleId },
        process.env.JWT_KEY,
        { expiresIn: "48h" }
      );

      res.status(200).json({
        token,
        expirationTime: new Date(
          new Date().getTime() + 1000 * 60 * 60 * 48
        ).getTime(),
      });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(500).json({ error: "un errro occured" });
    });
};

//------------------------------------------------------------------------------------POST /api/users/signup
const signup = async (req, res, next) => {
  const {
    cin,
    first_name,
    last_name,
    phone,
    email,
    password,
    image_path,
    roleId,
  } = req.body;

  User.create({
    cin,
    first_name,
    last_name,
    phone,
    email,
    password,
    image_path,
    isAuthorized: false,
    RoleId: +roleId,
  })
    .then((response) => {
      res.status(201).json({ response });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(500).json({ message: "un error occured" });
    });
};

//------------------------------------------------------------------------------------PUT /api/users/
const updateUser = async (req, res, next) => {
  const {
    id,
    cin,
    first_name,
    last_name,
    phone,
    email,
    password,
    image_path,
    isAuthorized,
    roleId,
  } = req.body;

  User.update(
    {
      cin,
      first_name,
      last_name,
      phone,
      email,
      password,
      image_path,
      isAuthorized,
      roleId,
    },
    { where: { id } }
  )
    .then((response) => {
      res.status(201).json({ message: `user with id ${id} updated` });
    })
    .catch((error) => {
      res.status(500).json({ message: "un error occured" });
    });
};

//------------------------------------------------------------------------------------DELETE /api/users/:id
const deleteUser = async (req, res, next) => {
  const id = +req.params["id"];
  if (!Number.isInteger(id))
    return res
      .status(404)
      .json({ error: "please provide a valid integer as a user id" });

  User.destroy({ where: { id: id } })
    .then((response) => {
      if (response === 0)
        return res.status(404).json({ message: "no user with this id " + id });
      res.status(201).json({ message: `User with id ${id} deleted...` });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(500).json({
        message: "Un error occured",
      });
    });
};

module.exports = { getUsers, login, signup, updateUser, deleteUser };
