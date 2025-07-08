import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/axios";
import { Event } from "../../types/Event";

export const fetchUpcomingEvents = createAsyncThunk("events/fetchUpcoming", async () => {
  const res = await api.get<Event[]>("/events/upcoming?page=0&size=10");
  return res.data;
});

const eventSlice = createSlice({
  name: "event",
  initialState: {
    events: [] as Event[],
    loading: false,
    error: null as string | null,
  },
  reducers: {},
  extraReducers: builder => {
    builder
      .addCase(fetchUpcomingEvents.pending, state => {
        state.loading = true;
        state.error = null;
      })
      .addCase(fetchUpcomingEvents.fulfilled, (state, action) => {
        state.loading = false;
        state.events = action.payload;
      })
      .addCase(fetchUpcomingEvents.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message ?? "Error fetching events";
      });
  },
});

export default eventSlice.reducer;