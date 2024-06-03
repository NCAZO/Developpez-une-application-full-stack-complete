import {User} from "../user/user";

export class Article {
  id: number;
  title: String;
  content: String;
  created_at: Date;
  user: User;
}
