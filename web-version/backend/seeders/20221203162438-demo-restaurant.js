"use strict";

const { faker } = require('@faker-js/faker');
const isPrime = require('../util/isPrime.js');

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    const restaurants = generateFakeRestaurants(24);
    await queryInterface.bulkInsert("restaurants", restaurants, {});
  },

  async down(queryInterface, Sequelize) {
    /**
     * Add commands to revert seed here.
     *
     * Example:
     * await queryInterface.bulkDelete('People', null, {});
     */
  },
};


function generateFakeRestaurants(rowsCount) {

  const restaurants = [];
  let counter = 0, primeNum = 3;

  while(counter < rowsCount) {

    if(!isPrime(primeNum)) {
      primeNum++;
      continue;
    }

    const menu = [];
    for(let j = 0; j < Math.floor(Math.random()*50); j++) {
      menu.push({
        name: faker.commerce.productName(),
        price: faker.commerce.price(0.5, 100),
        description: faker.commerce.productDescription()
      });
    }
 
    const newRestaurant = {
      name: faker.company.name() + ' Restaurant',
      address: 'Beja ' + faker.address.streetAddress(),
      menu: JSON.stringify(menu),
      rating: faker.datatype.float({ min: 1, max: 5, precision: 0.1 }),
      UserId: primeNum,
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    }
    restaurants.push(newRestaurant);
    counter++, primeNum++;

  }

  return restaurants;
}
