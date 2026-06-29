import { BrowserRouter, Routes, Route } from "react-router-dom";
import { CartProvider } from "./context/CartContext";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import RestaurantDetail from "./pages/RestaurantDetail";

function App() {
  return (
    <CartProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/restaurants/:id" element={<RestaurantDetail />} />
        </Routes>
      </BrowserRouter>
    </CartProvider>
  );
}

export default App;
