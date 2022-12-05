'use strict';
const { faker } = require('@faker-js/faker');
const isPrime = require('../util/isPrime.js');

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    const orders = generateFakeOrders(90_000);
    await queryInterface.bulkInsert('customer_orders', orders, {});
  },

  async down (queryInterface, Sequelize) {
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  }
};


function generateFakeOrders(rowsNum) {
  const orders = [];

  const validClientId = () => {
    let clientId = Math.ceil(Math.random() * 50_000);
    while(clientId < 100 && isPrime(clientId))
      clientId = Math.ceil(Math.random() * 50_000);
    return clientId;
  }

  for(let i = 0; i < rowsNum; i++) {
    const meals = [];
    for(let j = 0; j < Math.random() * 10; j++) {
      const newMeal = {
        name: faker.commerce.productName(),
        unitPrice: faker.datatype.float({ min: 0, max: 40, precision: 0.1 }),
        quantity: Math.ceil(Math.random()*10),
      };
      meals.push(newMeal);
    }

    const ordered_at = faker.date.past(12);
    const newOrder = {
      meals: JSON.stringify(meals),
      ordered_at,
      UserId: validClientId(),
      RestaurantId: Math.ceil(Math.random()*24),
      createdAt: ordered_at,
      updatedAt: ordered_at
    };

    orders.push(newOrder);
  }

  return orders;
}


