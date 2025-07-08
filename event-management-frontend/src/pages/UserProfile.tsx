import { useEffect, useState } from "react";
import api from "../api/axios";
import { Event } from "../types/Event";
import { Link } from "react-router-dom";
import React from "react";

const UserProfile = () => {
  const [events, setEvents] = useState<Event[]>([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  // You would typically get this from JWT or Redux
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (!userId) {
      setError("User not logged in.");
      return;
    }

    setLoading(true);
    api.get<Event[]>(`/events/user/${userId}`)
      .then(res => setEvents(res.data))
      .catch(() => setError("Failed to fetch events"))
      .finally(() => setLoading(false));
  }, [userId]);

  return (
    <div className="user-profile">
      <h2>Your Events</h2>
      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {events.length === 0 && !loading && <p>No events found</p>}

      {events.map(event => (
        <div key={event.id}>
          <Link to={`/events/${event.id}`}>
            <h4>{event.title}</h4>
          </Link>
          <p>{event.location}</p>
          <p>{new Date(event.startTime).toLocaleDateString()}</p>
        </div>
      ))}
    </div>
  );
};

export default UserProfile;