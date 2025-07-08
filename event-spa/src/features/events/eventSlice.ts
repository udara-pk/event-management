import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../api/axios";

export const fetchEvents = createAsyncThunk("events/fetch", async () => {
  const res = await api.get("/events/upcoming?page=0&size=10");
  return res.data;
});

const eventSlice = createSlice({
  name: "event",
  initialState: {
    events: [],
    loading: false,
    error: null,
  },
  reducers: {},
  extraReducers(builder) {
    builder
      .addCase(fetchEvents.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchEvents.fulfilled, (state, action) => {
        state.loading = false;
        state.events = action.payload;
      })
      .addCase(fetchEvents.rejected, (state) => {
        state.loading = false;
        state.error = "Failed to fetch events";
      });
  },
});

export default eventSlice.reducer;