export class Stock {
  constructor(
    public id: number,
    public title: string,
    public type_stock: string,
    public description: string,
    public RestaurantId: number,
    public createdAt: string,
    public updatedAt: string,
    public Products: [any]
  ) {}
}
