'use strict';

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {

    await queryInterface.bulkInsert('roles', [{
      type_of_user: 'admin',
      createdAt: new Date(),
      updatedAt: new Date()
    },{
      type_of_user: 'restaurant',
      createdAt: new Date(),
      updatedAt: new Date()
    }, {
      type_of_user: 'client',
      createdAt: new Date(),
      updatedAt: new Date()
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
