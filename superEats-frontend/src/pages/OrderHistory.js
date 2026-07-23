import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { getOrdersByUser } from "../api/orderApi";

function OrderHistory() {
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");
    const userId = localStorage.getItem("userId");

    if (!token) {
      navigate("/login", { state: { from: "/orders" } });
      return;
    }

    const fetchOrders = async () => {
      try {
        const data = await getOrdersByUser(userId);
        setOrders(data);
      } catch (err) {
        setError("Failed to load orders");
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, [navigate]);

  if (loading) {
    return <p className="text-center mt-10">Loading orders...</p>;
  }

  if (error) {
    return <p className="text-center mt-10 text-red-500">{error}</p>;
  }

  if (orders.length === 0) {
    return (
      <div className="p-6 text-center">
        <h1 className="text-2xl font-bold mb-4">No orders yet</h1>
        <button
          onClick={() => navigate("/")}
          className="text-orange-500 font-semibold"
        >
          Browse Restaurants
        </button>
      </div>
    );
  }

  return (
    <div className="p-6 max-w-3xl mx-auto">
      <h1 className="text-3xl font-bold text-orange-500 mb-6">
        Order History
      </h1>

      <div className="space-y-6">
        {orders.map((order) => (
          <div
            key={order.orderId}
            className="bg-white rounded-lg shadow-md p-6"
          >
            <div className="flex justify-between items-center mb-4">
              <div>
                <h2 className="text-lg font-bold">
                  Order #{order.orderId}
                </h2>
                <p className="text-gray-500">{order.restaurantName}</p>
              </div>
              <div className="text-right">
                <span
                  className={`text-sm font-semibold px-3 py-1 rounded-full ${
                    order.status === "DELIVERED"
                      ? "bg-green-100 text-green-600"
                      : order.status === "CANCELLED"
                      ? "bg-red-100 text-red-500"
                      : "bg-orange-100 text-orange-500"
                  }`}
                >
                  {order.status}
                </span>
                <p className="text-gray-700 font-bold mt-1">
                  ₹{order.totalPrice}
                </p>
              </div>
            </div>

            <div className="border-t pt-4 space-y-2">
              {order.items.map((item) => (
                <div
                  key={item.menuItemId}
                  className="flex justify-between text-sm text-gray-600"
                >
                  <span>{item.menuItemName} x {item.quantity}</span>
                  <span>₹{item.priceSnapshot * item.quantity}</span>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default OrderHistory;