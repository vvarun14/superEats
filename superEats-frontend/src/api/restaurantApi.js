import axiosInstance from "./axiosInstance";

export const getAllRestaurants = async () => {
  const response = await axiosInstance.get("/restaurants");
  return response.data;
};

export const getRestaurantById = async (id) => {
  const response = await axiosInstance.get(`/restaurants/${id}`);
  return response.data;
};
