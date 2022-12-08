const express = require("express");
const router = express.Router();
const checkAuth = require('../middleware/check-auth.js');
const feedbackDeliveryStaffsController = require("../controllers/feedback-delivery-staffs-controller");


//router.use(checkAuth); // authentication check
router.get("/", feedbackDeliveryStaffsController.getFeedback_delivery_staffs); // need authorization
router.post("/", feedbackDeliveryStaffsController.addFeedback_delivery_staffs); // need authorization
router.put("/", feedbackDeliveryStaffsController.updateFeedback_delivery_staffs); // need authorization
router.delete("/:id", feedbackDeliveryStaffsController.deleteFeedback_delivery_staffs); // need authorization

module.exports = router;