'use strict';

/** @type {import('sequelize-cli').Migration} */

const { faker } = require('@faker-js/faker');

module.exports = {
  async up (queryInterface, Sequelize) {
    const deliveryStaffs = generateFakeDeliveryStaffs(100);
    await queryInterface.bulkInsert('delivery_staffs', deliveryStaffs, {});
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


function generateFakeDeliveryStaffs(rowsNum) {
  const deliveryStaffs = [];

  const insertedCin = {};
  const cin = () => {
    let condidate = faker.random.numeric(8);
    while(insertedCin[condidate])
      condidate = faker.random.numeric(8);
    
    insertedCin[condidate] = true;
    return condidate;
  }

  for(let i = 0; i < rowsNum; i++) {

    const newDeliveryStaff = {
      cin: cin(),
      phone: faker.phone.number('+216 ## ### ###'),
      image_path: faker.internet.avatar(),
      first_name: faker.name.firstName(),
      last_name: faker.name.lastName(),
      RestaurantId: Math.ceil(Math.random()*24),
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    };

    deliveryStaffs.push(newDeliveryStaff);
  }

  return deliveryStaffs;
}