import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../hooks/useRedux";
import { fetchUpcomingEvents } from "../features/event/eventSlice";
import { Link } from "react-router-dom";
import React from "react";

const Dashboard = () => {
  const dispatch = useAppDispatch();
  const { events, loading, error } = useAppSelector(state => state.event);

  useEffect(() => {
    dispatch(fetchUpcomingEvents());
  }, [dispatch]);

  return (
    <div className="dashboard">
      <h2>Upcoming Events</h2>
      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "red" }}>{error}</p>}
      {events.map(event => (
        <div key={event.id}>
          <Link to={`/events/${event.id}`}>
            <h4>{event.title}</h4>
          </Link>
          <p>{event.location} | {new Date(event.startTime).toLocaleDateString()}</p>
        </div>
      ))}
    </div>
  );
};

export default Dashboard;