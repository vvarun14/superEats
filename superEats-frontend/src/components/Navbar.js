import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";

function Navbar() {
  const { items } = useCart();
  const itemCount = items.reduce((sum, item) => sum + item.quantity, 0);

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
      </div>
    </nav>
  );
}

export default Navbar;
