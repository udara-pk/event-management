import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../api/axios";
import { Event } from "../types/Event";
import React from "react";

const EventDetail = () => {
  const { id } = useParams();
  const [event, setEvent] = useState<Event | null>(null);

  useEffect(() => {
    api.get(`/events/${id}`).then(res => setEvent(res.data));
  }, [id]);

  if (!event) return <p>Loading...</p>;

  return (
    <div>
      <h2>{event.title}</h2>
      <p>{event.description}</p>
      <p>{event.location}</p>
      <p>{new Date(event.startTime).toLocaleString()}</p>
      <p>Attendees: {event.attendeeCount}</p>
    </div>
  );
};

export default EventDetail;