import {User} from "../user/user";
import {Theme} from "../theme/theme";

export class Article {
  id: number;
  title: String;
  content: String;
  theme: Theme;
  created_at: Date;
  user: User;
}
