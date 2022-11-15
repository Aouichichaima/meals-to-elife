const express = require("express");
const router = express.Router();
const stockControllers = require("../controllers/stocks-controller.js");

router.get("/", stockControllers.getStocks);
router.post("/", stockControllers.addStock);
router.put("/", stockControllers.updateStock);
router.delete("/:id", stockControllers.deleteStock);

module.exports = router;