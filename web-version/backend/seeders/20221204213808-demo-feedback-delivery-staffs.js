'use strict';

const { faker } = require('@faker-js/faker');

/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up (queryInterface, Sequelize) {
    const feedbacks = generateFakeFeedbacks(1_000);
    await queryInterface.bulkInsert('feedback_delivery_staffs', feedbacks, {});
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


function generateFakeFeedbacks(rowsNum) {
  const feedbacks = [];

  for(let i = 0; i < rowsNum; i++) {
    const newFeedback = {
      rating: faker.datatype.float({ min: 0, max: 9.9, precision: 0.1 }),
      comment: faker.lorem.sentence(),
      DeliveryStaffId: Math.ceil(Math.random()*100),
      createdAt: faker.date.past(12),
      updatedAt: faker.date.recent()
    };

    feedbacks.push(newFeedback);
  }

  return feedbacks;
}