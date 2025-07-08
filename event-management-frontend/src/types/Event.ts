export interface Event {
    id: string;
    title: string;
    description: string;
    hostId: string;
    startTime: string;
    endTime: string;
    location: string;
    visibility: "PUBLIC" | "PRIVATE";
    attendeeCount: number;
  }