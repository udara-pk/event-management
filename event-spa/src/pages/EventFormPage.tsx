import { useState } from "react";
import api from "../api/axios";

export default function EventFormPage() {
  const [form, setForm] = useState({
    title: "",
    description: "",
    startTime: "",
    endTime: "",
    location: "",
    visibility: "PUBLIC",
  });

  const handleChange = (e: any) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e: any) => {
    e.preventDefault();
    await api.post("/events", form);
    alert("Event created");
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create Event</h2>
      <input name="title" value={form.title} onChange={handleChange} required />
      <textarea name="description" value={form.description} onChange={handleChange} />
      <input type="datetime-local" name="startTime" onChange={handleChange} required />
      <input type="datetime-local" name="endTime" onChange={handleChange} required />
      <input name="location" value={form.location} onChange={handleChange} required />
      <select name="visibility" value={form.visibility} onChange={handleChange}>
        <option value="PUBLIC">Public</option>
        <option value="PRIVATE">Private</option>
      </select>
      <button type="submit">Submit</button>
    </form>
  );
}