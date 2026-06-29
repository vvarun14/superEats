import { createContext, useContext, useState } from "react";

const CartContext = createContext();

export function CartProvider({ children }) {
  const [restaurantId, setRestaurantId] = useState(null);
  const [restaurantName, setRestaurantName] = useState("");
  const [items, setItems] = useState([]);

  const addToCart = (item, currentRestaurantId, currentRestaurantName) => {
    if (restaurantId !== null && restaurantId !== currentRestaurantId) {
      const confirmSwitch = window.confirm(
        "Your cart contains items from another restaurant. Clear cart and add this item instead?",
      );
      if (!confirmSwitch) return;

      setItems([{ ...item, quantity: 1 }]);
      setRestaurantId(currentRestaurantId);
      setRestaurantName(currentRestaurantName);
      return;
    }

    setRestaurantId(currentRestaurantId);
    setRestaurantName(currentRestaurantName);

    setItems((prevItems) => {
      const existingItem = prevItems.find((i) => i.id === item.id);
      if (existingItem) {
        return prevItems.map((i) =>
          i.id === item.id ? { ...i, quantity: i.quantity + 1 } : i,
        );
      }
      return [...prevItems, { ...item, quantity: 1 }];
    });
  };

  const removeFromCart = (itemId) => {
    setItems((prevItems) => prevItems.filter((i) => i.id !== itemId));
  };

  const updateQuantity = (itemId, quantity) => {
    if (quantity <= 0) {
      removeFromCart(itemId);
      return;
    }
    setItems((prevItems) =>
      prevItems.map((i) => (i.id === itemId ? { ...i, quantity } : i)),
    );
  };

  const clearCart = () => {
    setItems([]);
    setRestaurantId(null);
    setRestaurantName("");
  };

  return (
    <CartContext.Provider
      value={{
        restaurantId,
        restaurantName,
        items,
        addToCart,
        removeFromCart,
        updateQuantity,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  return useContext(CartContext);
}
