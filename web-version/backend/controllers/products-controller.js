/**
 * @author djebby <djebby.firas@gmail.com>
 */

const { Product } = require('../models');

//------------------------------------------------------------------------------------GET /api/products/:stockId
const getProductsOfStock = async (req, res, next) => {
    
    const stockId = +req.params['stockId'];

    if(!Number.isInteger(stockId)) {
        return res.status(404).json({ error: "please provide a valid integer as a stockId" });
    }

    Product.findAll({
        where: { stockId }
    }).then(products => {
        res.status(200).json(products);
    }).catch(error => {
        console.log(error.message);
        res.status(500).json({error: "un error occured in the server"});
    });
}


//------------------------------------------------------------------------------------POST /api/products/
const addProductToStock = async (req, res, next) => {

    const { name, price, quantity, description, stockId } = req.body;

    Product.create({ name, price, quantity, description, stockId })
    .then(response => {
        res.status(201).json({ response });
    })
    .catch(error => {
        console.log(error.message);
        res.status(500).json({error : error.message });
    });

}


//------------------------------------------------------------------------------------PUT /api/products
const updateProduct = async (req, res, next) => {

    const { id, name, price, quantity, description, stockId } = req.body;
    Product.update({ name, price, quantity, description, stockId }, { where: { id }})
    .then( _ => {
        res.status(201).json({ message: `product with id : ${id} updated` });
    }).catch( error => {
        console.log(error.message);
        res.status(500).json({ message: 'un error occured in the server' });
    });
};


//------------------------------------------------------------------------------------DELETE /api/products
const deleteProduct = async (req, res, next) => {
    const id = +req.params['id'];
    if(!Number.isInteger(id))
        return res.status(404).json({ error: "please provide a valid integer as a product id" });

    Product.destroy({ where: { id }})
    .then(response => {
        res.status(201).json({
            message: `Product with id ${id} deleted...`
        });
    }).catch(error => {
        console.log(error.message);
        res.status(500).json({
            message: "Un error occured",
        })
    });
}



module.exports = { getProductsOfStock, addProductToStock, updateProduct, deleteProduct };