import api from './axios';
export const getEvents = () => api.get('/events/upcoming');
export const getEventById = (id: string) => api.get(`/events/${id}`);
export const createEvent = (data: any) => api.post('/events', data);
export const updateEvent = (id: string, data: any) => api.put(`/events/${id}`, data);