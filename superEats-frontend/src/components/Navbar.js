import { Link, useNavigate } from "react-router-dom";
import { useCart } from "../context/CartContext";
import { useState, useEffect } from "react";

function Navbar() {
  const { items, clearCart } = useCart();
  const itemCount = items.reduce((sum, item) => sum + item.quantity, 0);
  const navigate = useNavigate();

  const [username, setUsername] = useState("");

  useEffect(() => {
    const name = localStorage.getItem("name");
    if (name) setUsername(name);
  }, []);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("userId");
    localStorage.removeItem("name");
    clearCart();
    setUsername("");
    navigate("/login");
  };

  return (
    <nav className="bg-white shadow-md px-6 py-4 flex justify-between items-center">
      <Link to="/" className="text-2xl font-bold text-orange-500">
        SuperEats
      </Link>

      <div className="flex items-center gap-6">
        <Link to="/cart" className="relative text-gray-700 font-medium">
          Cart
          {itemCount > 0 && (
            <span className="absolute -top-2 -right-3 bg-orange-500 text-white text-xs rounded-full px-1.5 py-0.5">
              {itemCount}
            </span>
          )}
        </Link>

        {username ? (
          <div className="flex items-center gap-4">
                      <span className="text-gray-700 font-medium">Hi, {username}</span>
                      <Link to="/orders" className="text-gray-700 font-medium">
                        My Orders
                      </Link>
                      <button
                        onClick={handleLogout}
                        className="bg-orange-500 text-white px-3 py-1 rounded hover:bg-orange-600 text-sm"
                      >
                        Logout
                      </button>
                    </div>
        ) : (
          <div className="flex items-center gap-4">
            <Link to="/login" className="text-gray-700 font-medium">
              Login
            </Link>
            <Link
              to="/register"
              className="bg-orange-500 text-white px-3 py-1 rounded hover:bg-orange-600 text-sm"
            >
              Register
            </Link>
          </div>
        )}
      </div>
    </nav>
  );
}

export default Navbar;