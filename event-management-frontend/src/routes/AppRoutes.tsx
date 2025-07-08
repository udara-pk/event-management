import { Routes, Route } from "react-router-dom";
import { Suspense, lazy } from "react";
import React from "react";

// Lazy-loaded pages
const Dashboard = lazy(() => import("../pages/Dashboard"));
const EventDetail = lazy(() => import("../pages/EventDetail"));
const EventForm = lazy(() => import("../pages/EventForm"));
const UserProfile = lazy(() => import("../pages/UserProfile"));

const AppRoutes = () => (
  <Suspense fallback={<div>Loading...</div>}>
    <Routes>
      <Route path="/" element={<Dashboard />} />
      <Route path="/events/:id" element={<EventDetail />} />
      <Route path="/events/:id/edit" element={<EventForm />} />
      <Route path="/events/new" element={<EventForm />} />
      <Route path="/profile" element={<UserProfile />} />
    </Routes>
  </Suspense>
);

export default AppRoutes;