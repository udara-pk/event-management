import { useEffect, useState } from "react";
import api from "../api/axios";

export default function ProfilePage() {
  const [events, setEvents] = useState<any[]>([]);

  useEffect(() => {
    api.get("/events/user").then((res) => setEvents(res.data));
  }, []);

  return (
    <div>
      <h2>My Events</h2>
      <ul>
        {events.map((event) => (
          <li key={event.id}>{event.title}</li>
        ))}
      </ul>
    </div>
  );
}