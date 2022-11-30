const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth.js');
const stockControllers = require("../controllers/stocks-controller.js");

router.use(checkAuth); // authentication check
router.get("/", stockControllers.getStocks); // need authorization
router.post("/", stockControllers.addStock); // need authorization
router.put("/", stockControllers.updateStock); // need authorization
router.delete("/:id", stockControllers.deleteStock); // need authorization

module.exports = router;