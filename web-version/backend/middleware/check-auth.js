const jwt = require("jsonwebtoken");

module.exports = (req, res, next) => {
  if (req.method === "OPTIONS") return next();

  try {
    let token = undefined;
    if ("authorization" in req.headers)
      token = req.headers.authorization.split(' ')[1]; // authorization: Bearer jsonwebtoken
    if (!token)
      return res.status(403).json({
        message: "Please try to provide a token...",
      });
    const tokenPayload = jwt.verify(token, process.env.JWT_KEY);
    req.userData = {
      id: tokenPayload.userId,
      email: tokenPayload.userEmail,
    };
    next();
  } catch (error) {
    console.log(error.message);
    return res.status(500).json({
      message: "Authentication failed",
    });
  }
};
