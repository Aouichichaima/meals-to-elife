/**
 * @author ichraktoueiti <ichrak.touaiti@esprit.tn>
 */


 const  { CustomerOrder } =  require('../models');
 //------------------------------------------------------------------------------------GET /api/customer-orders/?bucketNum=int

 const getCustomerOrders = async (req, res, next) => {
    const ORDERS_BUCKET_SIZE = 100;
    const bucketNum = +req.query.bucketNum || 0;
     // check authorization req.userData.id (from the token)
    
     CustomerOrder.findAll({ where: { RestaurantId : 10}, offset: bucketNum * ORDERS_BUCKET_SIZE, limit: ORDERS_BUCKET_SIZE}).then( customerOrders => {
        res.status(200).json({ data: customerOrders  });
     
    }).catch(error => {
        console.log(error.message);
        res.status(404).json({ error: "Un error occured..." });
    });
};

//------------------------------------------------------------------------------------POST /api/customer-orders

const addCustomerOrder =  async (req, res, next) => { 
 
     // check authorization req.userData.id (from the token)
 const {  meals,ordered_at, UserId, RestaurantId}  = req.body;
 CustomerOrder.create({meals,ordered_at, UserId, RestaurantId}).then(response => {
    res.status(200).json({ message: "CustomerOrder created...",response});
}).catch(error => {
    console.log(error.message);
    res.status(404).json({ error: "Un error occured" });
});
}
//------------------------------------------------------------------------------------PUT /api/customer-orders
const updateCustomerOrder = async (req, res, next) => {

    // check authorization req.userData.id (from the token) 
    const {id,meals,ordered_at, UserId, RestaurantId} = req.body;
    CustomerOrder.update ({meals,ordered_at, UserId, RestaurantId}, { where: { id }})
    .then( () => {
        res.status(201).json({
            message: `CustomerOrder with id ${id} updated...`
        })
    })
    .catch(error => {
        console.log(error.message);
        res.status(404).json({
            message: error.message
        })
    });
}
//------------------------------------------------------------------------------------DELETE /api/customer-orders/:id
const deleteCustomerOrder = async (req, res, next) =>{

   // check authorization req.userData.id (from the token)
   const id = req.params['id'];
   CustomerOrder.destroy({ where: { id }})
       .then(response => {
           res.status(201).json({
               message: `CustomerOrder with id ${id} deleted...`
           })
       }).catch(error => {
           console.log(error.message);
           res.status(404).json({
               message: "Un error occured"
           })
       })
}

module.exports = {getCustomerOrders,addCustomerOrder,updateCustomerOrder,deleteCustomerOrder};