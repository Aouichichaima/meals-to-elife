/**
 * @author djebby <djebby.firas@gmail.com>
 */

const { Product, Stock } = require("../models");

//------------------------------------------------------------------------------------GET /api/stocks/
const getStocks = async (req, res, next) => {
  // check authorization req.userData.id (from the token)
  let restaurantId = 5;

  Stock.findAll({
    where: {
      RestaurantId: restaurantId,
    },
    include: { model: Product },
  })
    .then((stocks) => {
      res.status(200).json(stocks);
    })
    .catch((error) => {
      console.log(error.message);
      res.status(404).json({ error: "Un error occured..." });
    });
};

//------------------------------------------------------------------------------------POST /api/stocks/
const addStock = async (req, res, next) => {
  // check authorization req.userData.id (from the token)

  const { title, typeStock, description } = req.body;

  Stock.create({
    title,
    type_stock: typeStock,
    description,
    RestaurantId: 5, // we should conclude the id of the restaurant from the user id wich decoded from the token...
  })
    .then((response) => {
      res.status(200).json({ response });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(404).json({ error: "Un error occured" });
    });
};

//------------------------------------------------------------------------------------PUT /api/stocks/
const updateStock = async (req, res, next) => {
  // check authorization req.userData.id (from the token)

  const { id, title, typeStock, description } = req.body;

  Stock.update(
    { title, type_stock: typeStock, description },
    { where: { id } }
  )
    .then(() => {
      res.status(201).json({
        message: `Stock with id ${id} updated...`,
      });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(404).json({
        message: error.message,
      });
    });
};

//------------------------------------------------------------------------------------DELETE /api/stocks/:id
const deleteStock = async (req, res, next) => {
  // check authorization req.userData.id (from the token)

  const id = +req.params["id"];

  if (!Number.isInteger(id)) {
    return res
      .status(404)
      .json({ error: "please provide a valid integer as a stock id" });
  }

  Stock.destroy({ where: { id: id } })
    .then((response) => {
      if (response == 0)
        return res
          .status(404)
          .json({ message: `stock with id ${id} does not exist...` });
      res.status(201).json({
        message: `Stock with id ${id} deleted...`,
      });
    })
    .catch((error) => {
      console.log(error.message);
      res.status(500).json({
        message: "Un error occured",
      });
    });
};

module.exports = { getStocks, addStock, updateStock, deleteStock };
