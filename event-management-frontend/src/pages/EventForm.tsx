import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import api from "../api/axios";
import { Event } from "../types/Event";
import React from "react";

const defaultForm: Partial<Event> = {
  title: "",
  description: "",
  location: "",
  startTime: "",
  endTime: "",
  visibility: "PUBLIC",
};

const EventForm = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [form, setForm] = useState<Partial<Event>>(defaultForm);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const isEdit = Boolean(id);

  useEffect(() => {
    if (isEdit) {
      api.get(`/events/${id}`)
        .then((res) => setForm(res.data))
        .catch(() => setError("Event not found"));
    }
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement | HTMLSelectElement>) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    if (!form.title || !form.startTime || !form.endTime || !form.location) {
      setError("Please fill required fields");
      return;
    }

    setLoading(true);
    setError(null);

    try {
      if (isEdit) {
        await api.put(`/events/${id}`, form);
      } else {
        await api.post("/events", form);
      }
      navigate("/");
    } catch (err) {
      setError("Failed to save event");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="event-form">
      <h2>{isEdit ? "Edit Event" : "Create New Event"}</h2>
      {error && <p style={{ color: "red" }}>{error}</p>}
      <form onSubmit={handleSubmit} className="form">
        <label>Title*</label>
        <input name="title" value={form.title || ""} onChange={handleChange} required />

        <label>Description</label>
        <textarea name="description" value={form.description || ""} onChange={handleChange} />

        <label>Location*</label>
        <input name="location" value={form.location || ""} onChange={handleChange} required />

        <label>Start Time*</label>
        <input type="datetime-local" name="startTime" value={form.startTime || ""} onChange={handleChange} required />

        <label>End Time*</label>
        <input type="datetime-local" name="endTime" value={form.endTime || ""} onChange={handleChange} required />

        <label>Visibility</label>
        <select name="visibility" value={form.visibility || "PUBLIC"} onChange={handleChange}>
          <option value="PUBLIC">Public</option>
          <option value="PRIVATE">Private</option>
        </select>

        <button type="submit" disabled={loading}>
          {loading ? "Saving..." : isEdit ? "Update Event" : "Create Event"}
        </button>
      </form>
    </div>
  );
};

export default EventForm;