/**
 * @author ichraktoueiti <ichrak.touaiti@esprit.tn>
 */

 const { Restaurant } = require('../models');
 
 //------------------------------------------------------------------------------------GET /api/restaurants/
 const getRestaurants = async (req, res, next) => {
     
     // check authorization req.userData.id (from the token)
 
     Restaurant.findAll().then(restaurants => {
         res.status(200).json({ data: restaurants });
     }).catch(error => {
         console.log(error.message);
         res.status(404).json({ error: "Un error occured..." });
     });
 }
 
 
 //------------------------------------------------------------------------------------POST /api/restaurants/
 const addRestaurant = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token)
 
     const {  name, address,menu,rating, UserId } = req.body;
 
     Restaurant.create({  name, address,menu,rating, UserId}).then(response => {
         res.status(200).json({ message: "Restaurant created...",  name, address,menu,rating, UserId });
     }).catch(error => {
         console.log(error.message);
         res.status(404).json({ error: "Un error occured" });
     });
 }
 
 
 //------------------------------------------------------------------------------------PUT /api/restaurants/
 const updateRestaurant = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token) 
 
     const { id, name, address,menu,rating, UserId} = req.body;
 
     Restaurant.update({ name, address,menu,rating, UserId }, { where: { id }})
             .then( () => {
                 res.status(201).json({
                     message: `Restaurants with id ${id} updated...`
                 })
             })
             .catch(error => {
                 console.log(error.message);
                 res.status(404).json({
                     message: error.message
                 })
             });
 }
 //------------------------------------------------------------------------------------DELETE /api/restaurants/:id
 const deleteRestaurant = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token)
 
     const id = req.params['id'];
     Restaurant.destroy({ where: { id }})
         .then(response => {
             res.status(201).json({
                 message: `Restaurant with id ${id} deleted...`
             })
         }).catch(error => {
             console.log(error.message);
             res.status(404).json({
                 message: "Un error occured"
             })
         })
 }
 
 
 module.exports = { getRestaurants, addRestaurant, updateRestaurant, deleteRestaurant };