import { Routes, Route } from "react-router-dom";
import { Suspense, lazy } from "react";

const DashboardPage = lazy(() => import("../pages/DashboardPage"));
const EventDetailPage = lazy(() => import("../pages/EventDetailPage"));
const EventFormPage = lazy(() => import("../pages/EventFormPage"));
const ProfilePage = lazy(() => import("../pages/ProfilePage"));

export default function AppRoutes() {
  return (
    <Suspense fallback={<div style={{ textAlign: "center" }}>Loading...</div>}>
      <Routes>
        <Route path="/" element={<DashboardPage />} />
        <Route path="/events/:id" element={<EventDetailPage />} />
        <Route path="/events/new" element={<EventFormPage />} />
        <Route path="/events/edit/:id" element={<EventFormPage />} />
        <Route path="/profile" element={<ProfilePage />} />
      </Routes>
    </Suspense>
  );
}