/**
 * @author ichraktoueiti <ichrak.touaiti@esprit.tn>
 */

const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth.js');
const restaurantControllers = require("../controllers/restaurants-controller.js");

//router.use(checkAuth); // authentication check
router.get("/", restaurantControllers.getRestaurants); // need authorization
router.post("/", restaurantControllers.addRestaurant); // need authorization
router.put("/", restaurantControllers.updateRestaurant); // need authorization
router.delete("/:id", restaurantControllers.deleteRestaurant); // need authorization


module.exports = router;