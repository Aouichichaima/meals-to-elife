'use strict';

/** @type {import('sequelize-cli').Migration} */
const { faker } = require('@faker-js/faker');

module.exports = {
  async up (queryInterface, Sequelize) {
    const stocks = generateFakeStocks(1000);
    await queryInterface.bulkInsert('stocks', stocks, {});
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


function generateFakeStocks(rowsNum) {
  const stocks = [];

  for(let i = 0; i < rowsNum; i++) {

    const newStock = {
      title: faker.commerce.department(),
      type_stock: faker.commerce.productAdjective(),
      description: faker.commerce.productDescription(),
      RestaurantId: Math.ceil(Math.random()*24),
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    }

    stocks.push(newStock);
  }
  return stocks;
}
