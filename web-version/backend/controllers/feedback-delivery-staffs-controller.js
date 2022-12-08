/**
 * @author Aouichichaima <chaima.aouichi@esprit.tn>
 */

 const { FeedbackDelivery_staffs } = require("../models");



 //------------------------------------------------------------------------------------GET /api/feedback-delivery-staffs/
 const getFeedback_delivery_staffs = async (req, res, next) => {
     
     // check authorization req.userData.id (from the token)
 
     FeedbackDelivery_staffs.findAll().then(feedback_delivery_staffs => {
         res.status(200).json({ data: feedback_delivery_staffs });
     }).catch(error => {
         console.log(error.message);
         res.status(404).json({ error: "Un error occured..." });
     });
 }
 
 //------------------------------------------------------------------------------------POST /api/feedback-delivery-staffs/
 const addFeedback_delivery_staffs = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token)
 
     const { id,rating,comment } = req.body;
 
     FeedbackDelivery_staffs.create({ id,rating,comment }).then(response => {
         res.status(200).json({ message: "Feedback_delivery-staffs created...",id,rating,comment});
     }).catch(error => {
         console.log(error.message);
         res.status(404).json({ error: "Un error occured" });
     });
 }
 
 //------------------------------------------------------------------------------------PUT /api/feedback-delivery-staffs/
 const updateFeedback_delivery_staffs= async (req, res, next) => {
 
     // check authorization req.userData.id (from the token) 
 
     const { id,rating,comment } = req.body;
 
     FeedbackDelivery_staffs.update({ id,rating,comment }, { where: { id }})
             .then( () => {
                 res.status(201).json({
                     message: `Feedback with id ${id} updated...`
                 })
             })
             .catch(error => {
                 console.log(error.message);
                 res.status(404).json({
                     message: error.message
                 })
             });
 }
 
 //------------------------------------------------------------------------------------DELETE /api/feedback-delivery-staffs/:id
 const deleteFeedback_delivery_staffs = async (req, res, next) => {
 
     // check authorization req.userData.id (from the token)
 
     const id = req.params['id'];
     FeedbackDelivery_staffs.destroy({ where: { id: id}})
         .then(response => {
             res.status(201).json({
                 message: `Feedback with id ${id} deleted...`
             })
         }).catch(error => {
             console.log(error.message);
             res.status(404).json({
                 message: "Un error occured"
             })
         })
 }
 
 
 module.exports = { getFeedback_delivery_staffs, addFeedback_delivery_staffs, updateFeedback_delivery_staffs, deleteFeedback_delivery_staffs };