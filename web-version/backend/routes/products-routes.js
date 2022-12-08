/**
 * @author djebby <djebby.firas@gmail.com>
 */

const express = require("express");
const router = express.Router();

const productsController = require('../controllers/products-controller.js');

router.get('/:stockId', productsController.getProductsOfStock);
router.post('/', productsController.addProductToStock);
router.put('/', productsController.updateProduct);
router.delete('/:id', productsController.deleteProduct);



module.exports = router;