import matplotlib.pyplot as plt
import csv

def draw(x_data, y_data, title, x_label, y_label, interval=0.2):
    plt.ion()
    _, axes = plt.subplots()
    axes.set_title(title)
    axes.set_xlabel(x_label)
    axes.set_ylabel(y_label)

    line, = axes.plot([], [], marker="o")
    plt.show()

    for i in range(len(x_data)):
        line.set_xdata(x_data[:i + 1])
        line.set_ydata(y_data[:i + 1])
        axes.relim()
        axes.autoscale_view()

        print(x_data[i], y_data[i])

        plt.draw()
        plt.pause(interval)

    plt.ioff()
    plt.show()

def read_data_from_csv(file_name):
    x_data = []
    y_data = []
    with open(file_name, newline='') as csv_file:
        csv_reader = csv.reader(csv_file)

        labels = next(csv_reader)
        x_label = labels[0]
        y_label = labels[1]

        for row in csv_reader:
            x_data.append(float(row[0]))
            y_data.append(float(row[1]))

    return x_data, y_data, x_label, y_label

def main():
    file_name = "data.csv"
    chart_title = file_name

    x_data, y_data, x_label, y_label = read_data_from_csv(file_name)

    draw(x_data, y_data, chart_title, x_label, y_label)


if __name__ == "__main__":
    main()
