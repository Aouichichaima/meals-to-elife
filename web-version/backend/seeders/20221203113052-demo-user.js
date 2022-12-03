'use strict';

const { faker } = require('@faker-js/faker');

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    
    await queryInterface.bulkInsert('users', [{
      name: 'John Doe',
      isBetaMember: false
    }], {});
    
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



function generateFakeUsers(rowsCount) {
  const users = [];

  for(let i = 0; i < rowsCount; i++) {
    const newUser = {
      cin: faker.random.numeric(8),
      first_name: faker
      last_name
      phone
      email
      password
      image_path
      isAuthorized
      RoleId
    }
    users.push(newUser);
  }

  return users;
}