import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../hooks/reduxHooks";
import { fetchEvents } from "../features/event/eventSlice";

export default function DashboardPage() {
  const dispatch = useAppDispatch();
  const { events, loading, error } = useAppSelector((state) => state.event);

  useEffect(() => {
    dispatch(fetchEvents());
  }, [dispatch]);

  return (
    <div className="container">
      <h1>Upcoming Events</h1>
      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      <ul>
        {events.map((event: any) => (
          <li key={event.id}>
            <a href={`/events/${event.id}`}>{event.title}</a>
          </li>
        ))}
      </ul>
    </div>
  );
}