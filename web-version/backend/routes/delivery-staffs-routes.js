const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth.js');
const deliveryStaffControllers = require("../controllers/delivery-staffs-controller.js");



//router.use(checkAuth); // authentication check
router.get("/",deliveryStaffControllers.getDeliveryStaffs); // need authorization
router.post("/",deliveryStaffControllers.addDeliveryStaffs); // need authorization
router.put("/", deliveryStaffControllers.updateDeliveryStaffs); // need authorization
router.delete("/:id",deliveryStaffControllers.deleteDeliveryStaffs); // need authorization

module.exports = router;