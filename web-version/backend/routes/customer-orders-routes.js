/**
 * @author ichraktoueiti <ichrak.touaiti@esprit.tn>
 */

 const express = require("express");
 const router = express.Router();
 const checkAuth = require('../middleware/check-auth.js');
 const customerorderControllers = require("../controllers/customer-orders-controller.js")

 //router.use(checkAuth); // authentication check

router.get("/",customerorderControllers.getCustomerOrders); 
router.post("/",customerorderControllers.addCustomerOrder);
router.put("/",customerorderControllers.updateCustomerOrder);
router.delete("/:id",customerorderControllers.deleteCustomerOrder);




module.exports = router;