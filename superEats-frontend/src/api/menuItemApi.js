import axiosInstance from "./axiosInstance";

export const getMenuItemsByRestaurant = async (restaurantId) => {
  const response = await axiosInstance.get(
    `/menu-items/restaurant/${restaurantId}`,
  );
  return response.data;
};
