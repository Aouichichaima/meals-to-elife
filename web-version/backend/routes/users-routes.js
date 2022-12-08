const express = require('express');
const router = express.Router();

const userControllers = require('../controllers/users-controllers.js');

router.get('/', userControllers.getUsers);
router.post('/login', userControllers.login);
router.post('/signup', userControllers.signup);
router.put('/', userControllers.updateUser);
router.delete('/:id', userControllers.deleteUser);

module.exports = router;