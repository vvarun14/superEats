import { useEffect, useState } from "react";
import { getAllRestaurants } from "../api/restaurantApi";

function Home() {
  const [restaurants, setRestaurants] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRestaurants = async () => {
      try {
        const data = await getAllRestaurants();
        setRestaurants(data);
      } catch (err) {
        setError("Failed to load restaurants");
      } finally {
        setLoading(false);
      }
    };

    fetchRestaurants();
  }, []);

  if (loading) {
    return <p className="text-center mt-10">Loading restaurants...</p>;
  }

  if (error) {
    return <p className="text-center mt-10 text-red-500">{error}</p>;
  }

  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold text-orange-500 mb-6">Restaurants</h1>

      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-4">
        {restaurants.map((restaurant) => (
          <div
            key={restaurant.id}
            className="bg-white rounded-lg shadow-md p-4 hover:shadow-lg transition"
          >
            <h2 className="text-xl font-semibold">{restaurant.name}</h2>
            <p className="text-gray-500">{restaurant.address}</p>
            <p
              className={`mt-2 text-sm font-medium ${
                restaurant.isOpen ? "text-green-600" : "text-red-500"
              }`}
            >
              {restaurant.isOpen ? "Open" : "Closed"}
            </p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Home;
