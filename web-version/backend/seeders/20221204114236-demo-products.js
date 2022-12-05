'use strict';

/** @type {import('sequelize-cli').Migration} */

const { faker } = require('@faker-js/faker');

module.exports = {
  async up (queryInterface, Sequelize) {
    const products = generateFakeProducts(10_000);
    await queryInterface.bulkInsert('products', products, {});
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


function generateFakeProducts(rowsCount) {
  
  const products = [];

  for(let i = 0; i < rowsCount; i++) {
    const newProduct = {
      name: faker.commerce.productName(),
      price: faker.commerce.price(),
      quantity: Math.ceil(Math.random()*999),
      description: faker.commerce.productDescription(),
      stockId: Math.ceil(Math.random()*1000),
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    };

    products.push(newProduct);
  }
  
  return products;

}