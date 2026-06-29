import { useEffect, useState } from "react";
import { useParams, Link } from "react-router-dom";
import { getRestaurantById } from "../api/restaurantApi";
import { getMenuItemsByRestaurant } from "../api/menuItemApi";

function RestaurantDetail() {
  const { id } = useParams();
  const [restaurant, setRestaurant] = useState(null);
  const [menuItems, setMenuItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const restaurantData = await getRestaurantById(id);
        const menuData = await getMenuItemsByRestaurant(id);
        setRestaurant(restaurantData);
        setMenuItems(menuData);
      } catch (err) {
        setError("Failed to load restaurant details");
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [id]);

  if (loading) {
    return <p className="text-center mt-10">Loading...</p>;
  }

  if (error) {
    return <p className="text-center mt-10 text-red-500">{error}</p>;
  }

  return (
    <div className="p-6">
      <Link to="/" className="text-orange-500 font-semibold">
        ← Back to Restaurants
      </Link>

      <h1 className="text-3xl font-bold mt-4">{restaurant.name}</h1>
      <p className="text-gray-500">{restaurant.address}</p>
      <p
        className={`mt-2 text-sm font-medium ${
          restaurant.open ? "text-green-600" : "text-red-500"
        }`}
      >
        {restaurant.open ? "Open" : "Closed"}
      </p>

      <h2 className="text-2xl font-semibold mt-8 mb-4">Menu</h2>

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
        {menuItems.map((item) => (
          <div key={item.id} className="bg-white rounded-lg shadow-md p-4">
            <h3 className="text-lg font-semibold">{item.name}</h3>
            <p className="text-gray-600">₹{item.price}</p>
            <p
              className={`text-sm mt-1 ${
                item.available ? "text-green-600" : "text-red-500"
              }`}
            >
              {item.available ? "Available" : "Unavailable"}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default RestaurantDetail;
