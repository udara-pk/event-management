import { Routes, Route } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import EventDetail from "../pages/EventDetail";
import EventForm from "../pages/EventForm";
import UserProfile from "../pages/UserProfile";

const AppRoutes = () => (
  <Routes>
    <Route path="/" element={<Dashboard />} />
    <Route path="/events/:id" element={<EventDetail />} />
    <Route path="/events/:id/edit" element={<EventForm />} />
    <Route path="/events/new" element={<EventForm />} />
    <Route path="/profile" element={<UserProfile />} />
  </Routes>
);

export default AppRoutes;