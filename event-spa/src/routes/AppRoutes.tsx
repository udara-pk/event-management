import { Routes, Route } from "react-router-dom";
import DashboardPage from "../pages/DashboardPage";
import EventDetailPage from "../pages/EventDetailPage";
import EventFormPage from "../pages/EventFormPage";
import ProfilePage from "../pages/ProfilePage";

export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<DashboardPage />} />
      <Route path="/events/:id" element={<EventDetailPage />} />
      <Route path="/events/new" element={<EventFormPage />} />
      <Route path="/events/edit/:id" element={<EventFormPage />} />
      <Route path="/profile" element={<ProfilePage />} />
    </Routes>
  );
}