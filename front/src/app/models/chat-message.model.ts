export interface ChatMessage {
    chat_message_id?: number;
    support_request_id: number;
    user_id: number;
    text: string;
    created_at?: string;
}
