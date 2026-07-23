import axiosInstance from "./axiosInstance";

export const placeOrder = async (orderData) => {
  const token = localStorage.getItem("token");
  const response = await axiosInstance.post("/orders", orderData, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

export const getOrderById = async (id) => {
  const token = localStorage.getItem("token");
  const response = await axiosInstance.get(`/orders/${id}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};

export const getOrdersByUser = async (userId) => {
  const token = localStorage.getItem("token");
  const response = await axiosInstance.get(`/orders/user/${userId}`, {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  return response.data;
};
