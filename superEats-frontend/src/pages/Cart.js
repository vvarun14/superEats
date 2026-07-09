import { Link } from "react-router-dom";
import { useCart } from "../context/CartContext";
import { useNavigate } from "react-router-dom";

function Cart() {
  const { restaurantName, items, updateQuantity, removeFromCart, clearCart } =
    useCart();

  const navigate = useNavigate();

  const handlePlaceOrder = () => {
    const token = localStorage.getItem("token");

    if (!token) {
      const confirm = window.confirm(
        "You need to be logged in to place an order. Go to Login?",
      );
      if (confirm) {
        navigate("/login", { state: { from: "/cart" } });
      }
      return;
    }
    alert("Placing order...");
  };

  const total = items.reduce(
    (sum, item) => sum + item.price * item.quantity,
    0,
  );

  if (items.length === 0) {
    return (
      <div className="p-6 text-center">
        <h1 className="text-2xl font-bold mb-4">Your cart is empty</h1>
        <Link to="/" className="text-orange-500 font-semibold">
          Browse Restaurants
        </Link>
      </div>
    );
  }

  return (
    <div className="p-6 max-w-2xl mx-auto">
      <Link to="/" className="text-orange-500 font-semibold">
        ← Continue Shopping
      </Link>

      <h1 className="text-3xl font-bold mt-4 mb-2">Your Cart</h1>
      <p className="text-gray-500 mb-6">Ordering from: {restaurantName}</p>

      <div className="space-y-4">
        {items.map((item) => (
          <div
            key={item.id}
            className="bg-white rounded-lg shadow-md p-4 flex justify-between items-center"
          >
            <div>
              <h3 className="font-semibold">{item.name}</h3>
              <p className="text-gray-600">₹{item.price}</p>
            </div>

            <div className="flex items-center gap-3">
              <button
                onClick={() => updateQuantity(item.id, item.quantity - 1)}
                className="bg-gray-200 px-2 py-1 rounded"
              >
                −
              </button>
              <span>{item.quantity}</span>
              <button
                onClick={() => updateQuantity(item.id, item.quantity + 1)}
                className="bg-gray-200 px-2 py-1 rounded"
              >
                +
              </button>

              <button
                onClick={() => removeFromCart(item.id)}
                className="text-red-500 text-sm ml-3"
              >
                Remove
              </button>
            </div>
          </div>
        ))}
      </div>

      <div className="mt-6 flex justify-between items-center border-t pt-4">
        <h2 className="text-xl font-bold">Total: ₹{total.toFixed(2)}</h2>
        <button onClick={clearCart} className="text-sm text-gray-500 underline">
          Clear Cart
        </button>
      </div>

      <button
        onClick={handlePlaceOrder}
        className="w-full mt-6 bg-orange-500 text-white py-3 rounded hover:bg-orange-600 font-semibold"
      >
        Place Order
      </button>
    </div>
  );
}

export default Cart;
