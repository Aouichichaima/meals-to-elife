/**
 * @author Aouichichaima<chaima.aouichi@esprit.tn>
 */


const { DeliveryStaff } = require("../models");


 //------------------------------------------------------------------------------------GET /api/delivery-staffs/
 const getDeliveryStaffs = async (req, res, next) => {
     
     // check authorization req.userData.id (from the token)
 
     DeliveryStaff.findAll().then(delivery_staffs => {
         res.status(200).json({ data: delivery_staffs });
     }).catch(error => {
         console.log(error.message);
         res.status(404).json({ error: "Un error occured..." });
     });
 }
 
 //------------------------------------------------------------------------------------POST /api/delivery-staffs/
 const addDeliveryStaffs = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token)
 
     const { cin, phone,first_name,last_name } = req.body;
    console.log('addDeliveryStaffs controllers...');
     DeliveryStaff.create({ cin,phone, image_path:"some-url.com/avatar.jpg",first_name,last_name ,RestaurantId:5}).then(response => {
         res.status(201).json({ message: "Delivery-staffs created...",response});
     }).catch(error => {
         console.log(error.message);
         res.status(404).json({ error: "Un error occured" });
     });
 }
 
 //------------------------------------------------------------------------------------PUT /api/delivery-staffs/
 const updateDeliveryStaffs= async (req, res, next) => {
 
     // check authorization req.userData.id (from the token) 
 
     const { id,cin,phone,image_path,first_name,last_name } = req.body;
 
     DeliveryStaff.update({ id,cin,phone,image_path,first_name,last_name }, { where: { id }})
             .then( () => {
                 res.status(201).json({
                     message: `Delivery with id ${id} updated...`
                 })
             })
             .catch(error => {
                 console.log(error.message);
                 res.status(404).json({
                     message: error.message
                 })
             });
 }
 
 //------------------------------------------------------------------------------------DELETE /api/delivery-staffs/:id
 const deleteDeliveryStaffs = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token)
 
     const id = req.params['id'];
     DeliveryStaff.destroy({ where: { id: id}})
         .then(response => {
             res.status(201).json({
                 message: `Delivery with id ${id} deleted...`
             })
         }).catch(error => {
             console.log(error.message);
             res.status(404).json({
                 message: "Un error occured"
             })
         })
 }
 
 
 module.exports = { getDeliveryStaffs,addDeliveryStaffs, updateDeliveryStaffs, deleteDeliveryStaffs };