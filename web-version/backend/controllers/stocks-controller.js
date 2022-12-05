/**
 * @author djebby <djebby.firas@gmail.com>
 */

const {Product, Restaurant, Role, Stock, User} = require("../models");


//------------------------------------------------------------------------------------GET /api/stocks/
const getStocks = async (req, res, next) => {
    
    // check authorization req.userData.id (from the token)

    Stock.findAll({
        where : {
            RestaurantId: 4
        },
        include: {model: Product}
    }).then(stocks => {
        res.status(200).json({ data: stocks });
    }).catch(error => {
        console.log(error.message);
        res.status(404).json({ error: "Un error occured..." });
    });
}

//------------------------------------------------------------------------------------POST /api/stocks/
const addStock = async (req, res, next) => {

    // check authorization req.userData.id (from the token)

    const { title, typeStock, description, restaurantId } = req.body;

    Stock.create({ title, type_stock: typeStock, description, RestaurantId: +restaurantId }).then(response => {
        res.status(200).json({ message: "Stock created...", title, typeStock, description, restaurantId });
    }).catch(error => {
        console.log(error.message);
        res.status(404).json({ error: "Un error occured" });
    });
}

//------------------------------------------------------------------------------------PUT /api/stocks/
const updateStock = async (req, res, next) => {

    // check authorization req.userData.id (from the token) 

    const { id, title, typeStock, description, restaurantId } = req.body;

    Stock.update({ title, type_stock: typeStock, description, RestaurantId: +restaurantId }, { where: { id }})
            .then( () => {
                res.status(201).json({
                    message: `Stock with id ${id} updated...`
                })
            })
            .catch(error => {
                console.log(error.message);
                res.status(404).json({
                    message: error.message
                })
            });
}

//------------------------------------------------------------------------------------DELETE /api/stocks/:id
const deleteStock = async (req, res, next) => {

    // check authorization req.userData.id (from the token)

    const id = +req.params['id'];

    if(!Number.isInteger(id)) {
        return res.status(404).json({ error: "please provide a valid integer as a stockId" });
    }

    Stock.destroy({ where: { id: id}})
        .then(response => {
            console.log(response);
            res.status(201).json({
                message: `Stock with id ${id} deleted...`
            });
        }).catch(error => {
            console.log(error.message);
            res.status(500).json({
                message: "Un error occured",
            })
        });
}


module.exports = { getStocks, addStock, updateStock, deleteStock };