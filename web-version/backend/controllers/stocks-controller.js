const Stock = require("../models/stocks-model.js");


//------------------------------------------------------------------------------------GET /api/stocks/
const getStocks = async (req, res, next) => {
    
    Stock.findAll().then(stocks => {
        res.status(200).json({ data: stocks });
    }).catch(error => {
        console.log(error.message);
        res.status(404).json({ error: "Un error occured..." });
    });
}

//------------------------------------------------------------------------------------POST /api/stocks/
const addStock = async (req, res, next) => {
    const { title, typeStock, description } = req.body;

    Stock.create({ title, type_stock: typeStock, description }).then(response => {
        res.status(200).json({ message: "Stock created...", title, typeStock, description });
    }).catch(error => {
        console.log(error.message);
        res.status(404).json({ error: "Un error occured" });
    });
}

//------------------------------------------------------------------------------------PUT /api/stocks/
const updateStock = async (req, res, next) => {
    const { id, title, typeStock, description } = req.body;

    Stock.update({ title, type_stock: typeStock, description }, { where: { id }})
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

//------------------------------------------------------------------------------------PUT /api/stocks/:id
const deleteStock = async (req, res, next) => {
    const id = req.params['id'];
    Stock.destroy({ where: { id: id}})
        .then(response => {
            res.status(201).json({
                message: `Stock with id ${id} deleted...`
            })
        }).catch(error => {
            console.log(error.message);
            res.status(404).json({
                message: "Un error occured"
            })
        })
}


module.exports = { getStocks, addStock, updateStock, deleteStock };